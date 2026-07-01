# 🏋️ FitZone — Full Master Prompt
### Gym Website · React 18 + Spring Boot 3 · Full-Stack

> **How to use:** Paste this entire prompt into Claude, ChatGPT, Cursor, or any AI coding tool.
> Ask it to build one section at a time starting from the bottom "Build Order" section.

---

## 🎯 PROJECT OVERVIEW

Build a complete, production-ready gym website called **FitZone**.

- **Frontend:** React 18 + Vite
- **Backend:** Spring Boot 3 (Java 17)
- **Database:** MySQL (prod) / H2 (dev)
- **Design:** Modern dark/light theme, red + black palette, bold typography
- **Auth:** JWT-based, Role system (ADMIN / MEMBER / TRAINER)

The site has three zones:
1. **Public site** — marketing pages anyone can view
2. **Member portal** — dashboard, bookings, membership status
3. **Admin panel** — full CRUD control over all data

---

## 🎨 DESIGN SYSTEM

```
Primary:     #E63946  (red — CTAs, highlights, badges)
Dark bg:     #0D0D0D  (near-black for dark mode hero)
Surface:     #1A1A1A  (cards, panels in dark mode)
Light bg:    #F8F8F8  (light mode background)
Text:        #FFFFFF / #111111 (dark / light modes)
Accent gray: #6B7280

Display font:  "Bebas Neue" (headings, hero, stat counters)
Body font:     "Inter"       (paragraphs, UI labels, nav)
Mono font:     "JetBrains Mono" (code snippets, badges)
```

**UX Rules:**
- Mobile-first responsive (sm / md / lg / xl breakpoints)
- Dark mode toggle — persisted in `localStorage`
- Smooth scroll-reveal on all section entries (Framer Motion)
- Page transitions: fade + slight vertical slide
- All interactive elements have focus rings for accessibility
- Loading skeletons on every async data fetch
- Toast notifications (react-hot-toast) for all user actions

---

## ⚙️ FRONTEND TECH STACK

| Package | Purpose |
|---|---|
| React 18 + Vite | App framework + build tool |
| React Router v6 | Client-side routing + protected routes |
| Axios | HTTP client with JWT interceptors |
| TanStack Query (React Query) | Server state, caching, background refetch |
| React Hook Form + Zod | Form management + schema validation |
| Framer Motion | Page transitions, scroll animations |
| Tailwind CSS | Utility-first styling |
| shadcn/ui | Component library built on Radix UI |
| Recharts | Charts in member dashboard + admin |
| react-hot-toast | Toast notifications |
| Lucide React | Icon set |
| date-fns | Date formatting |
| clsx + tailwind-merge | Conditional class merging |

---

## ⚙️ BACKEND TECH STACK

| Dependency | Purpose |
|---|---|
| Spring Boot 3.x | Application framework |
| Spring Security 6 | Auth & route protection |
| jjwt (JJWT 0.12) | JWT generation + validation |
| Spring Data JPA | ORM layer |
| Hibernate | JPA implementation |
| MySQL Connector | Production database |
| H2 | In-memory DB for dev/tests |
| Lombok | Boilerplate reduction (@Data, @Builder) |
| MapStruct | Entity ↔ DTO mapping |
| Cloudinary SDK | Image upload & CDN storage |
| JavaMailSender | Transactional emails |
| SpringDoc OpenAPI 2 | Swagger UI at `/swagger-ui.html` |
| Validation (Jakarta) | @Valid, @NotNull, @Email etc. |
| Docker + docker-compose | Containerization |

---

## 📁 FRONTEND FOLDER STRUCTURE

```
fitzone-frontend/
├── public/
│   └── favicon.ico
├── src/
│   ├── api/
│   │   ├── axiosInstance.js        # baseURL, JWT interceptor, 401 redirect
│   │   ├── authApi.js              # register, login, refreshToken
│   │   ├── classesApi.js           # getAll, getById, create, update, delete
│   │   ├── bookingsApi.js          # getMyBookings, book, cancel
│   │   ├── membersApi.js           # getAll, getById, update, dashboard
│   │   ├── trainersApi.js          # getAll, getById, CRUD
│   │   ├── membershipApi.js        # getPlans, updatePlan
│   │   ├── galleryApi.js           # getImages, upload, delete
│   │   └── contactApi.js           # sendMessage, getAll (admin)
│   │
│   ├── assets/
│   │   └── images/                 # hero.jpg, logo.svg, placeholder.png
│   │
│   ├── components/
│   │   ├── layout/
│   │   │   ├── Navbar.jsx          # sticky, mobile hamburger, dark mode toggle
│   │   │   ├── Footer.jsx          # links, socials, newsletter signup
│   │   │   └── AdminSidebar.jsx    # collapsible, icon+label nav
│   │   │
│   │   ├── ui/                     # shadcn/ui overrides + custom components
│   │   │   ├── Button.jsx
│   │   │   ├── Badge.jsx
│   │   │   ├── Card.jsx
│   │   │   ├── Modal.jsx
│   │   │   ├── Skeleton.jsx
│   │   │   └── Table.jsx
│   │   │
│   │   ├── home/
│   │   │   ├── HeroSection.jsx         # fullscreen, parallax bg, CTA buttons
│   │   │   ├── StatsCounter.jsx        # animated counters: members/classes/years
│   │   │   ├── FeaturedClasses.jsx     # 3-card grid preview
│   │   │   ├── TrainersPreview.jsx     # 3-card trainer teaser
│   │   │   ├── TestimonialsSlider.jsx  # auto-play carousel
│   │   │   ├── MembershipTeaser.jsx    # 3 plan cards with CTA
│   │   │   └── CTABanner.jsx           # "Start your journey" full-width strip
│   │   │
│   │   ├── auth/
│   │   │   ├── LoginForm.jsx           # email, password, remember me
│   │   │   └── RegisterForm.jsx        # name, email, password, plan picker
│   │   │
│   │   ├── classes/
│   │   │   ├── ClassCard.jsx           # image, trainer, time, intensity badge, book btn
│   │   │   ├── ClassFilter.jsx         # category chips: All/HIIT/Yoga/Strength/Boxing
│   │   │   ├── BookingModal.jsx        # confirm booking, date picker
│   │   │   └── ScheduleTable.jsx       # weekly grid view toggle
│   │   │
│   │   ├── membership/
│   │   │   └── PricingCard.jsx         # plan name, price, features, CTA
│   │   │
│   │   ├── trainers/
│   │   │   ├── TrainerCard.jsx         # photo, name, specialty, star rating
│   │   │   └── TrainerModal.jsx        # full bio, classes, weekly schedule
│   │   │
│   │   ├── dashboard/
│   │   │   ├── WelcomeCard.jsx         # member name, plan badge, expiry date
│   │   │   ├── MemberStats.jsx         # total bookings, classes attended, streak
│   │   │   ├── UpcomingBookings.jsx    # list with cancel button
│   │   │   ├── BookingHistoryTable.jsx # paginated past bookings
│   │   │   └── BMIHistoryChart.jsx     # Recharts LineChart of saved BMI entries
│   │   │
│   │   ├── admin/
│   │   │   ├── StatsOverview.jsx       # 4 KPI cards: members, revenue, bookings, classes
│   │   │   ├── MembersTable.jsx        # search, filter, activate/deactivate
│   │   │   ├── ClassesManager.jsx      # CRUD table + create form modal
│   │   │   ├── TrainersManager.jsx     # CRUD + photo upload
│   │   │   ├── BookingsTable.jsx       # all bookings, status filter
│   │   │   └── RevenueChart.jsx        # Recharts BarChart monthly revenue
│   │   │
│   │   └── shared/
│   │       ├── PrivateRoute.jsx        # checks auth + role
│   │       ├── LoadingSpinner.jsx      # centered spinner overlay
│   │       ├── ErrorBoundary.jsx       # catches render errors
│   │       ├── BMICalculator.jsx       # interactive calculator widget
│   │       ├── DarkModeToggle.jsx      # sun/moon icon button
│   │       └── ScrollToTop.jsx         # scroll to top on route change
│   │
│   ├── context/
│   │   └── AuthContext.jsx             # user state, login(), logout(), isAuthenticated
│   │
│   ├── hooks/
│   │   ├── useAuth.js                  # consume AuthContext
│   │   ├── useBooking.js               # book + cancel mutations
│   │   ├── useMembership.js            # current plan, expiry logic
│   │   └── useLocalStorage.js          # typed localStorage helper
│   │
│   ├── pages/
│   │   ├── Home.jsx
│   │   ├── About.jsx
│   │   ├── Classes.jsx
│   │   ├── Schedule.jsx
│   │   ├── Trainers.jsx
│   │   ├── MembershipPlans.jsx
│   │   ├── BMIPage.jsx
│   │   ├── Gallery.jsx
│   │   ├── Contact.jsx
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   ├── MemberDashboard.jsx
│   │   └── admin/
│   │       ├── AdminDashboard.jsx
│   │       ├── AdminMembers.jsx
│   │       ├── AdminClasses.jsx
│   │       ├── AdminTrainers.jsx
│   │       └── AdminBookings.jsx
│   │
│   ├── utils/
│   │   ├── bmiCalculator.js            # calculateBMI(weight, height) → { value, category color }
│   │   ├── formatDate.js               # format, timeAgo helpers
│   │   ├── classIntensity.js           # badge color by intensity level
│   │   └── constants.js                # API_URL, ROLES, PLAN_TYPES, DAYS_OF_WEEK
│   │
│   ├── App.jsx                         # all routes defined here
│   ├── main.jsx                        # ReactDOM.createRoot, QueryClientProvider
│   └── index.css                       # Tailwind directives + custom CSS vars
│
├── .env                                # VITE_API_BASE_URL=http://localhost:8080
├── tailwind.config.js                  # extended theme (colors, fonts, animation)
├── vite.config.js
└── package.json
```

---

## 📁 BACKEND FOLDER STRUCTURE

```
fitzone-backend/
├── src/
│   └── main/
│       ├── java/com/fitzone/api/
│       │   │
│       │   ├── config/
│       │   │   ├── SecurityConfig.java      # filter chain, CORS, public/protected routes
│       │   │   ├── JwtConfig.java           # secret key, expiry from properties
│       │   │   ├── CorsConfig.java          # allowed origins, methods, headers
│       │   │   └── CloudinaryConfig.java    # Cloudinary bean from properties
│       │   │
│       │   ├── controller/
│       │   │   ├── AuthController.java      # /api/auth/**
│       │   │   ├── ClassController.java     # /api/classes/**
│       │   │   ├── BookingController.java   # /api/bookings/**
│       │   │   ├── MemberController.java    # /api/members/**
│       │   │   ├── TrainerController.java   # /api/trainers/**
│       │   │   ├── MembershipController.java# /api/memberships/**
│       │   │   ├── GalleryController.java   # /api/gallery/**
│       │   │   └── ContactController.java   # /api/contact/**
│       │   │
│       │   ├── service/
│       │   │   ├── AuthService.java
│       │   │   ├── ClassService.java
│       │   │   ├── BookingService.java      # checks capacity, prevents double-booking
│       │   │   ├── MemberService.java
│       │   │   ├── TrainerService.java
│       │   │   ├── MembershipService.java
│       │   │   ├── GalleryService.java
│       │   │   ├── ContactService.java
│       │   │   ├── EmailService.java        # JavaMailSender templates
│       │   │   └── CloudinaryService.java   # upload/delete images
│       │   │
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── MemberRepository.java
│       │   │   ├── TrainerRepository.java
│       │   │   ├── GymClassRepository.java
│       │   │   ├── BookingRepository.java
│       │   │   ├── MembershipRepository.java
│       │   │   ├── GalleryRepository.java
│       │   │   └── ContactRepository.java
│       │   │
│       │   ├── model/                       # JPA Entities
│       │   │   ├── User.java                # id, name, email, password, role, createdAt
│       │   │   ├── Member.java              # id, user(FK), membership(FK), joinDate, status
│       │   │   ├── Trainer.java             # id, user(FK), bio, speciality, rating, photoUrl
│       │   │   ├── GymClass.java            # id, name, description, trainer(FK), dayOfWeek,
│       │   │   │                            #   startTime, durationMin, capacity, currentCount
│       │   │   ├── Booking.java             # id, member(FK), gymClass(FK), bookingDate, status
│       │   │   ├── Membership.java          # id, type(BASIC/PRO/ELITE), price, durationDays, features
│       │   │   ├── GalleryImage.java        # id, imageUrl, publicId, caption, uploadedAt
│       │   │   └── Contact.java             # id, name, email, subject, message, createdAt
│       │   │
│       │   ├── dto/
│       │   │   ├── request/
│       │   │   │   ├── LoginRequest.java        # email, password
│       │   │   │   ├── RegisterRequest.java     # name, email, password, membershipType
│       │   │   │   ├── BookingRequest.java      # classId, bookingDate
│       │   │   │   ├── ClassRequest.java        # all class fields
│       │   │   │   ├── TrainerRequest.java      # all trainer fields
│       │   │   │   └── ContactRequest.java      # name, email, subject, message
│       │   │   │
│       │   │   └── response/
│       │   │       ├── ApiResponse.java         # { success, data, message, timestamp }
│       │   │       ├── AuthResponse.java        # token, type, userId, email, role
│       │   │       ├── ClassResponse.java
│       │   │       ├── BookingResponse.java
│       │   │       ├── MemberResponse.java
│       │   │       ├── TrainerResponse.java
│       │   │       └── DashboardResponse.java   # stats + upcoming bookings + plan info
│       │   │
│       │   ├── security/
│       │   │   ├── JwtTokenProvider.java        # generateToken, validateToken, getClaims
│       │   │   ├── JwtAuthFilter.java           # OncePerRequestFilter, sets SecurityContext
│       │   │   └── UserDetailsServiceImpl.java  # loadUserByUsername from DB
│       │   │
│       │   ├── exception/
│       │   │   ├── GlobalExceptionHandler.java  # @ControllerAdvice, consistent JSON errors
│       │   │   ├── ResourceNotFoundException.java
│       │   │   ├── BookingConflictException.java# already booked / class full
│       │   │   ├── UnauthorizedException.java
│       │   │   └── InvalidTokenException.java
│       │   │
│       │   ├── mapper/
│       │   │   ├── ClassMapper.java             # MapStruct: GymClass ↔ ClassResponse
│       │   │   ├── BookingMapper.java
│       │   │   ├── TrainerMapper.java
│       │   │   └── MemberMapper.java
│       │   │
│       │   └── FitzoneApplication.java
│       │
│       └── resources/
│           ├── application.properties           # common config
│           ├── application-dev.properties       # H2, debug logging
│           ├── application-prod.properties      # MySQL, SSL, prod logging
│           ├── templates/email/                 # Thymeleaf HTML email templates
│           │   ├── booking-confirmation.html
│           │   ├── welcome.html
│           │   └── membership-expiry-warning.html
│           └── data.sql                         # seed: 3 admin, 10 members, 5 trainers,
│                                                #       10 classes, 3 membership plans
│
├── Dockerfile
├── docker-compose.yml                           # backend + MySQL + Adminer
└── pom.xml
```

---

## 🗃️ DATABASE SCHEMA

```sql
-- Users (base table for all roles)
CREATE TABLE users (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(100) NOT NULL,
  email        VARCHAR(150) NOT NULL UNIQUE,
  password     VARCHAR(255) NOT NULL,            -- BCrypt hash
  role         ENUM('ROLE_ADMIN','ROLE_MEMBER','ROLE_TRAINER') NOT NULL,
  is_active    BOOLEAN DEFAULT TRUE,
  created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Membership Plans
CREATE TABLE memberships (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  type           ENUM('BASIC','PRO','ELITE') NOT NULL,
  price          DECIMAL(8,2) NOT NULL,
  duration_days  INT NOT NULL,                   -- 30, 90, 365
  features       JSON                            -- ["Unlimited classes","Locker room",...]
);

-- Members
CREATE TABLE members (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id         BIGINT NOT NULL UNIQUE,
  membership_id   BIGINT,
  join_date       DATE NOT NULL,
  expiry_date     DATE,
  status          ENUM('ACTIVE','INACTIVE','EXPIRED') DEFAULT 'ACTIVE',
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (membership_id) REFERENCES memberships(id)
);

-- Trainers
CREATE TABLE trainers (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id     BIGINT NOT NULL UNIQUE,
  bio         TEXT,
  speciality  VARCHAR(100),
  rating      DECIMAL(2,1) DEFAULT 5.0,
  photo_url   VARCHAR(500),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Gym Classes
CREATE TABLE gym_classes (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  name            VARCHAR(100) NOT NULL,
  description     TEXT,
  category        ENUM('HIIT','YOGA','STRENGTH','CARDIO','BOXING') NOT NULL,
  intensity       ENUM('BEGINNER','INTERMEDIATE','ADVANCED') NOT NULL,
  trainer_id      BIGINT,
  day_of_week     ENUM('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY'),
  start_time      TIME NOT NULL,
  duration_min    INT NOT NULL,
  capacity        INT NOT NULL,
  current_count   INT DEFAULT 0,
  image_url       VARCHAR(500),
  is_active       BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (trainer_id) REFERENCES trainers(id)
);

-- Bookings
CREATE TABLE bookings (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  member_id     BIGINT NOT NULL,
  class_id      BIGINT NOT NULL,
  booking_date  DATE NOT NULL,
  status        ENUM('CONFIRMED','CANCELLED','ATTENDED') DEFAULT 'CONFIRMED',
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY no_double_book (member_id, class_id, booking_date),
  FOREIGN KEY (member_id) REFERENCES members(id),
  FOREIGN KEY (class_id) REFERENCES gym_classes(id)
);

-- Gallery
CREATE TABLE gallery (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  image_url   VARCHAR(500) NOT NULL,
  public_id   VARCHAR(200),                      -- Cloudinary public ID for deletion
  caption     VARCHAR(200),
  uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Contact Messages
CREATE TABLE contacts (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  email       VARCHAR(150) NOT NULL,
  subject     VARCHAR(200),
  message     TEXT NOT NULL,
  is_read     BOOLEAN DEFAULT FALSE,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🔐 AUTH & SECURITY

```
Flow:
  1. POST /api/auth/register
     → Validate DTO → BCrypt password → Save User + Member → Return JWT

  2. POST /api/auth/login
     → Validate credentials → Generate JWT (24hr expiry) → Return AuthResponse

  3. Every protected request:
     → JwtAuthFilter extracts "Bearer <token>" from Authorization header
     → Validates signature + expiry → Sets SecurityContext
     → @PreAuthorize("hasRole('ADMIN')") applied at controller level

Role Hierarchy:
  ROLE_ADMIN   → full access to everything
  ROLE_TRAINER → can view their own classes and member lists
  ROLE_MEMBER  → can book classes, view own data, manage own profile

JWT Payload:
  { sub: "user@email.com", role: "ROLE_MEMBER", userId: 5, iat: ..., exp: ... }

Token storage (frontend):
  → httpOnly cookie OR localStorage (configurable via .env flag)
  → Axios interceptor auto-attaches token to every request
  → On 401 response → clear token → redirect to /login
```

---

## 📄 ALL PAGES — FULL SPEC

### 1. HOME (/)
- Fullscreen hero: dark overlay on gym photo, "PUSH YOUR LIMITS" headline in Bebas Neue 80px, two CTA buttons ("Join Now" → /register, "See Classes" → /classes)
- Animated scroll-reveal stats bar: `500+ Members` · `20+ Classes` · `10 Expert Trainers` · `5 Years Strong`
- Featured Classes: 3 `ClassCard` components pulled from API (most popular)
- "Meet Your Trainers": 3 `TrainerCard` with photo + name + specialty
- Testimonials: auto-playing slider, 5 member quotes with star ratings
- Membership teaser: 3 `PricingCard` components (Basic/Pro/Elite) with "Get Started" CTA
- Final CTA banner: red background, "Start Your Fitness Journey Today" + "Join Now" button

### 2. ABOUT (/about)
- Gym founding story, mission statement
- 3-column facility highlights (Equipment / Showers & Lockers / Parking)
- Full team grid (all trainers)
- Photo gallery strip (5 images from gallery API)

### 3. CLASSES (/classes)
- Category filter chips at top (All / HIIT / Yoga / Strength / Cardio / Boxing)
- Responsive grid of `ClassCard` components
- Each card: class image, name, trainer name+photo, duration badge, intensity badge (color-coded), day/time, capacity bar (`X / Y spots`), "Book Now" button
- Toggle between Grid view and Schedule Table view
- `BookingModal` opens on "Book Now": shows class details, date picker, "Confirm Booking" button

### 4. SCHEDULE (/schedule)
- Weekly timetable grid (Mon–Sun columns, time rows)
- Each cell = class block with color by category
- Click block → opens `BookingModal`
- Filters: week picker, category toggle

### 5. MEMBERSHIP PLANS (/membership)
- 3 `PricingCard` components in a row:
  - **BASIC** — $29/mo — 2 classes/week, locker access
  - **PRO** — $59/mo — Unlimited classes, personal trainer session (most popular badge)
  - **ELITE** — $99/mo — PRO + nutrition plan + priority booking + guest pass
- Feature comparison table below cards
- FAQ accordion (5 questions)

### 6. TRAINERS (/trainers)
- Grid of all `TrainerCard` components from API
- Click → `TrainerModal` opens with: large photo, full bio, specialties tags, star rating, weekly schedule, "Book with [Name]" button

### 7. BMI CALCULATOR (/bmi)
- Form fields: Height (cm), Weight (kg), Age, Gender (Male/Female)
- Real-time BMI calculation on input change
- Color-coded result gauge (red → orange → yellow → green → yellow → orange → red)
- BMI value + category text: Underweight / Normal / Overweight / Obese
- Category-specific message + recommended membership plan
- "Save to my profile" button (auth required) → saves to member's BMI history

### 8. GALLERY (/gallery)
- Masonry image grid, loaded from Cloudinary via API
- Lightbox on click (full image, caption, prev/next arrows)
- Admin-only: upload button at top → multipart POST to `/api/gallery/upload`

### 9. CONTACT (/contact)
- Left: contact form (Name, Email, Subject, Message textarea) + submit → POST `/api/contact`
- Right: Google Maps embed, address, phone, email, opening hours card
- Success toast on submission

### 10. LOGIN (/login)
- Email + Password inputs with Zod validation
- "Remember me" checkbox
- "Forgot password?" link (placeholder)
- On success → JWT stored → redirect to /dashboard
- Links to /register

### 11. REGISTER (/register)
- Name, Email, Password, Confirm Password (all validated)
- Membership plan picker (3 radio cards)
- Terms & Conditions checkbox
- On success → auto-login → redirect to /dashboard

### 12. MEMBER DASHBOARD (/dashboard)
- `WelcomeCard`: "Welcome back, [Name]!" · PRO badge · Expires: Dec 31, 2025
- Stats row: Total Bookings · Classes This Month · Current Streak · Days Left
- `UpcomingBookings`: list of next 5 bookings, each with cancel button
- `BookingHistoryTable`: paginated table of all past bookings (date, class, trainer, status)
- `BMIHistoryChart`: Recharts LineChart of saved BMI entries over time
- "Quick Book" suggestions: 3 classes the member hasn't tried

### 13. ADMIN DASHBOARD (/admin)
- `StatsOverview`: 4 KPI cards → Total Members (↑12%), Revenue this month, Total Bookings, Active Classes
- `RevenueChart`: Recharts BarChart, last 6 months
- Recent members table (last 5 registered)
- Recent bookings table (last 5 bookings)

### 14. ADMIN — MEMBERS (/admin/members)
- Searchable, filterable DataTable (search by name/email, filter by plan/status)
- Columns: Name, Email, Plan, Join Date, Expiry, Status, Actions
- Actions: View profile, Deactivate/Activate, Delete (with confirm modal)

### 15. ADMIN — CLASSES (/admin/classes)
- CRUD table of all gym classes
- "Add Class" button → modal form (all class fields, trainer dropdown, image upload)
- Edit and Delete per row

### 16. ADMIN — TRAINERS (/admin/trainers)
- CRUD table of trainers
- "Add Trainer" button → modal form with Cloudinary photo upload
- Edit and Delete per row

### 17. ADMIN — BOOKINGS (/admin/bookings)
- All bookings table, filterable by class / date / status
- Status update dropdown per booking (Confirmed → Attended / Cancelled)

---

## 🔌 ALL API ENDPOINTS

```
AUTH
  POST   /api/auth/register                → RegisterRequest → AuthResponse
  POST   /api/auth/login                   → LoginRequest → AuthResponse
  POST   /api/auth/refresh-token           → refreshToken → new AuthResponse

MEMBERS                                    [MEMBER = own data, ADMIN = all]
  GET    /api/members                      (ADMIN) → List<MemberResponse>
  GET    /api/members/{id}                 → MemberResponse
  PUT    /api/members/{id}                 → MemberResponse
  DELETE /api/members/{id}                 (ADMIN)
  GET    /api/members/{id}/dashboard       → DashboardResponse
  PUT    /api/members/{id}/membership      (ADMIN) → update plan + expiry

CLASSES
  GET    /api/classes                      → List<ClassResponse> (filterable: ?category=HIIT)
  GET    /api/classes/{id}                 → ClassResponse
  GET    /api/classes/schedule             → grouped by day_of_week
  POST   /api/classes                      (ADMIN) → ClassResponse
  PUT    /api/classes/{id}                 (ADMIN) → ClassResponse
  DELETE /api/classes/{id}                 (ADMIN)

BOOKINGS
  GET    /api/bookings/my                  (MEMBER) → List<BookingResponse>
  POST   /api/bookings                     (MEMBER) → BookingResponse
                                             [checks capacity + no double booking]
  PUT    /api/bookings/{id}/cancel         (MEMBER) → BookingResponse
  PUT    /api/bookings/{id}/status         (ADMIN)  → update to ATTENDED/CANCELLED
  GET    /api/bookings                     (ADMIN)  → paginated all bookings

TRAINERS
  GET    /api/trainers                     → List<TrainerResponse>
  GET    /api/trainers/{id}                → TrainerResponse
  POST   /api/trainers                     (ADMIN) → TrainerResponse
  PUT    /api/trainers/{id}                (ADMIN) → TrainerResponse
  DELETE /api/trainers/{id}                (ADMIN)

MEMBERSHIPS
  GET    /api/memberships                  → List<Membership>
  POST   /api/memberships                  (ADMIN)
  PUT    /api/memberships/{id}             (ADMIN)

GALLERY
  GET    /api/gallery                      → List<GalleryImage>
  POST   /api/gallery/upload               (ADMIN) multipart/form-data → GalleryImage
  DELETE /api/gallery/{id}                 (ADMIN) → also deletes from Cloudinary

CONTACT
  POST   /api/contact                      → saves message + sends email to admin
  GET    /api/contact                      (ADMIN) → List<Contact>
  PUT    /api/contact/{id}/read            (ADMIN) → mark as read
```

### Standard API Response Wrapper
```json
{
  "success": true,
  "data": { },
  "message": "Operation successful",
  "timestamp": "2025-06-27T10:00:00Z"
}
```

### Standard Error Response
```json
{
  "success": false,
  "error": "BOOKING_CONFLICT",
  "message": "Class is full or you already have a booking for this session",
  "timestamp": "2025-06-27T10:00:00Z"
}
```

---

## 📧 EMAIL TRIGGERS

| Event | Template | Recipient |
|---|---|---|
| Successful registration | `welcome.html` | New member |
| Booking confirmed | `booking-confirmation.html` | Member |
| Booking cancelled | `booking-cancelled.html` | Member |
| Membership expiry (7 days) | `membership-expiry-warning.html` | Member |
| New contact message | plain text | Admin |

---

## 🌐 ENVIRONMENT VARIABLES

**Frontend (.env)**
```
VITE_API_BASE_URL=http://localhost:8080/api
VITE_TOKEN_STORAGE=localStorage
VITE_GOOGLE_MAPS_KEY=your_key
```

**Backend (application-prod.properties)**
```
spring.datasource.url=jdbc:mysql://db:3306/fitzone
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

jwt.secret=${JWT_SECRET}
jwt.expiry-ms=86400000

cloudinary.cloud-name=${CLOUDINARY_NAME}
cloudinary.api-key=${CLOUDINARY_KEY}
cloudinary.api-secret=${CLOUDINARY_SECRET}

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USER}
spring.mail.password=${MAIL_PASS}
```

---

## 🐳 DOCKER SETUP

**docker-compose.yml**
```yaml
version: '3.8'
services:
  db:
    image: mysql:8.0
    environment:
      MYSQL_DATABASE: fitzone
      MYSQL_ROOT_PASSWORD: ${DB_ROOT_PASS}
      MYSQL_USER: ${DB_USER}
      MYSQL_PASSWORD: ${DB_PASS}
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  adminer:
    image: adminer
    ports:
      - "8081:8080"

  backend:
    build: .
    ports:
      - "8080:8080"
    environment:
      DB_USER: ${DB_USER}
      DB_PASS: ${DB_PASS}
      JWT_SECRET: ${JWT_SECRET}
      CLOUDINARY_NAME: ${CLOUDINARY_NAME}
      CLOUDINARY_KEY: ${CLOUDINARY_KEY}
      CLOUDINARY_SECRET: ${CLOUDINARY_SECRET}
    depends_on:
      - db

volumes:
  mysql_data:
```

---

## 🚀 BUILD ORDER (Follow This Exactly)

```
PHASE 1 — Backend Foundation
  1. Spring Boot project setup (Spring Initializr)
  2. MySQL + JPA config, all Entity classes
  3. SecurityConfig (disable CSRF, CORS open for dev, stateless session)
  4. JWT: JwtTokenProvider, JwtAuthFilter, UserDetailsServiceImpl
  5. Auth: register + login endpoints — TEST with Postman

PHASE 2 — Backend Features
  6. Member + Membership CRUD
  7. Trainer CRUD
  8. GymClass CRUD
  9. Booking endpoint (with capacity check + duplicate prevention)
  10. Gallery (Cloudinary integration)
  11. Contact endpoint + email trigger
  12. GlobalExceptionHandler
  13. Swagger UI setup + seed data.sql

PHASE 3 — Frontend Foundation
  14. Vite + React + Tailwind + shadcn/ui setup
  15. AuthContext + axiosInstance (interceptors)
  16. App.jsx routes + PrivateRoute
  17. Navbar + Footer + DarkModeToggle

PHASE 4 — Public Pages
  18. Home page (all sections)
  19. Classes page + filter
  20. Schedule page
  21. Trainers page + modal
  22. Membership Plans page
  23. BMI Calculator page
  24. Gallery page
  25. About + Contact pages

PHASE 5 — Auth Pages
  26. Login page + form
  27. Register page + plan picker

PHASE 6 — Member Portal
  28. Member Dashboard
  29. Booking flow (modal + confirmation)
  30. Cancel booking

PHASE 7 — Admin Panel
  31. Admin layout (sidebar)
  32. Admin Dashboard (KPIs + charts)
  33. Members management
  34. Classes management
  35. Trainers management
  36. Bookings management

PHASE 8 — Polish
  37. Framer Motion animations (page transitions + scroll-reveal)
  38. Loading skeletons on all data fetches
  39. Error boundaries
  40. Mobile responsiveness audit
  41. Docker setup + docker-compose test
```

---

## ✅ QUALITY RULES

- **DTOs always** — never expose JPA Entity directly in API response
- **Validation everywhere** — @Valid on all @RequestBody, Zod on all forms
- **Consistent errors** — GlobalExceptionHandler returns ApiResponse with `success: false`
- **No secrets in code** — all secrets via environment variables only
- **Capacity enforcement** — BookingService checks `currentCount < capacity` in a `@Transactional` method
- **No double-booking** — UNIQUE constraint on (member_id, class_id, booking_date) + service-level check
- **Accessibility** — all images have alt text, all buttons have aria-labels, keyboard nav works
- **SEO** — each page sets document title + meta description
- **Loading states** — every async operation shows skeleton or spinner
- **Generate every file completely** — no `// TODO`, no `// implement later`, no placeholders

---

*FitZone Master Prompt — Generated by Claude · Ready to build 💪*
